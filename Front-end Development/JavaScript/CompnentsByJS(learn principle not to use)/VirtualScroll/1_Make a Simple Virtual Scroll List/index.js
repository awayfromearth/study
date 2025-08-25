/*
* 一、获取所需DOM元素
* */
const container = document.querySelector('#container')

const listLengthInput = document.querySelector("#num0")
const itemHeightInput = document.querySelector("#num1")
const startIndexInput = document.querySelector("#num2")
const containerHeightInput = document.querySelector("#num3")

const listLengthButton = document.querySelector("#btn0")
const itemHeightButton = document.querySelector("#btn1")
const startIndexButton = document.querySelector("#btn2")
const containerHeightButton = document.querySelector("#btn3")

/*
* 二、创建模拟数据
* */
/**
 * 根据输入的长度生成对应数量元素的数组
 * @param length 目标数组长度
 * @returns {Number[]} 模拟数据数组
 */
function getMockData(length) {
  const list = []
  for (let i = 1; i <= length; i++) {
    list.push(i)
  }
  return list
}
const list = getMockData(100000)

/*
* 三、渲染数据到DOM
* */
// 3.1、创建虚拟滚动类集成相关的功能
class VirtualScroll {
  constructor(container, list, itemHeight) {
    this.container = container
    this._list = list
    this._itemHeight = itemHeight
    this._startIndex = 1
    // 3.3、生成撑满窗口的容器
    this.wrapper = document.createElement("div")
    this.wrapper.style.height = `${this.totalHeight}px`
    this.container.appendChild(this.wrapper)
    // 3.4、获取容器高度
    if (ResizeObserver) {
      const resizeObserver = new ResizeObserver(mutations => {
        mutations.forEach(mutation => {
          if (mutation.target === container) {
            this._containerHeight = mutation.contentRect.height
            this.render(true)
          }
        })
      })
      resizeObserver.observe(this.container, { box: "content-box" })
    } else {
      this._containerHeight = parseFloat(getComputedStyle(container).height)
      this.render(true)
    }

    /*
    * 四、滚动时在可视区域更新DOM
    * */
    this.container.addEventListener("scroll", this.handleScroll.bind(this))
  }

  get totalHeight() {
    return this.list.length * this.itemHeight
  }

  get scrollTop() {
    return this.container.scrollTop
  }
  set scrollTop(value) {
    this.container.removeEventListener("scroll", this.handleScroll)
    this.container.scrollTop = Math.min(Math.max(0, value), this.totalHeight - this.containerHeight)
    this.render(true)
    requestAnimationFrame(() => this.container.addEventListener("scroll", this.handleScroll.bind(this)))
  }

  /*
  * 3.2、拆分要响应的可变参数，预设getter和setter
  * */
  get list() {
    return this._list
  }
  set list(value) {
    this._list = value
    this.wrapper.style.height = `${this.totalHeight}px`
    this.scrollTop = this.scrollTop
  }

  get itemHeight() {
    return this._itemHeight
  }
  set itemHeight(value) {
    this._itemHeight = value
    this.wrapper.style.height = `${this.totalHeight}px`
    // 保持滚动不偏移
    this.startIndex = this.startIndex
  }

  get startIndex() {
    return this._startIndex
  }
  set startIndex(value) {
    this._startIndex = value
    this.scrollTop = (value - 1) * this.itemHeight
  }

  get containerHeight() {
    return this._containerHeight
  }
  set containerHeight(value) {
    this._containerHeight = value
    this.container.style.height = `${value}px`
    this.render(true)
  }

  /**
   * 控制计算所得索引在[0, this.list.length-1]之间
   * @param i 原始索引
   * @returns {number} 经过限制后的索引
   */
  getLimitIndex(i) {
    return Math.min(Math.max(1, i), this.list.length)
  }

  handleScroll(e) {
    e.stopPropagation()
    // 4.1、滚动时重新渲染列表
    this.render()
  }

  render(force = false) {
    const oldStartIndex = this.startIndex
    // 4.2、计算新的startIndex
    const startIndex = this.getLimitIndex(Math.ceil(this.scrollTop / this.itemHeight + 1))
    this._startIndex = startIndex
    // 4.3、更新判断条件：滚动到某一个元素中间位置时不需要更新
    if (force || Math.abs(startIndex - oldStartIndex) > 0) {
      // 3.5、在可视范围内创建并添加DOM
      const $list = document.createDocumentFragment()
      const showCount = Math.ceil(this.containerHeight / this.itemHeight)
      const endIndex = this.getLimitIndex(this.startIndex + showCount)
      // 4.4、计算元素应有的偏移量
      const offset = (this.startIndex - 1) * this.itemHeight
      let item
      for (let i = this.startIndex; i <= endIndex; i++) {
        item = document.createElement("div")
        item.className = "item"
        item.innerText = `第${i}个`
        item.style.height = `${this.itemHeight}px`
        // 4.5、元素偏移填充塌陷区域
        item.style.transform = `translateY(${offset}px)`
        $list.appendChild(item)
      }
      this.wrapper.innerHTML = ""
      this.wrapper.appendChild($list)
      // 5.1.2、startIndex的值会随着滚动而变化在render中修改
      startIndexInput.value = this.startIndex
    }
  }
}

const vs = new VirtualScroll(container, list, 40)

/*
* 五、虚拟列表与输入框及按钮的交互
* */
// 5.1、输入框初始显示
// 5.1.1、除startIndex项外的回显
listLengthInput.value = vs.list.length
itemHeightInput.value = vs.itemHeight
containerHeightInput.value = parseFloat(getComputedStyle(container).height)

// 5.2、绑定按钮事件，在个变量对应的setter中更新列表
listLengthButton.addEventListener("click", () => {
  vs.list = getMockData(Number(listLengthInput.value))
})

itemHeightButton.addEventListener("click", () => {
  vs.itemHeight = Number(itemHeightInput.value)
})

startIndexButton.addEventListener("click", () => {
  vs.startIndex = Number(startIndexInput.value)
})

containerHeightButton.addEventListener("click", () => {
  vs.containerHeight = Number(containerHeightInput.value)
})