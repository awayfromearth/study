const container = document.getElementById("container")
const fragment = document.createDocumentFragment()

let div
for (let i = 0; i < 100000; i++) {
  div = document.createElement("div")
  div.innerText = `第${i}个`
  fragment.appendChild(div)
}

container.appendChild(fragment)