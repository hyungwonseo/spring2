const urlBoard = "/api/board";
const urlDeleteBoard = "/api/board/delete";

let dataList = [];
let pageCurrent = 1;
let pageEnd = 1;
const itemsPerPage = 5;

function getBoard() {
  axios
    .get(urlBoard, { withCredentials: true })
    .then((response) => {
      console.log("데이터:", response.data);
      dataList = response.data;
      pageEnd = Math.ceil(dataList.length / itemsPerPage);
      displayPageNum();
      displayBoardHead(1);
    })
    .catch((error) => {
      console.log("에러 발생: ", error);
    });
}

function displayBoardHead(page) {
  const startIndex = (page - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage - 1;
  const data =
    dataList.length > 0
      ? dataList.filter((d, i) => i >= startIndex && i <= endIndex)
      : [];
  console.log(data);
  if (data.length > 0) {
    const tbody = document.querySelector(".item-head");
    tbody.classList.remove("hidden");
    document.querySelector(".item-text").classList.add("hidden");
    tbody.innerHTML = "";

    for (let index = 0; index < itemsPerPage; index++) {
      if (index < data.length) {
        // 태그 요소 생성
        const tr = document.createElement("tr");
        const id = document.createElement("td");
        const title = document.createElement("td");
        const writer = document.createElement("td");
        const dTime = document.createElement("td");
        // 클래스이름 생성

        // 태그속성추가
        id.textContent = (page - 1) * itemsPerPage + index + 1;
        title.textContent = data[index].title;
        writer.textContent = data[index].author.userId;
        dTime.textContent = formatPurchaseDate(data[index].modifiedDate);
        // appendChild 부모자식 위치 설정
        tr.appendChild(id);
        tr.appendChild(title);
        tr.appendChild(writer);
        tr.appendChild(dTime);
        tbody.appendChild(tr);

        tr.addEventListener("click", () => {
          displayBoardText(data[index], id.textContent, page);
        });
      } else {
        const tr = document.createElement("tr");
        const td = document.createElement("td");
        td.classList.add("td-no-style");
        tr.appendChild(td);
        tbody.appendChild(tr);
      }
    }
    // data.forEach((d, index) => {
    //   // 태그 요소 생성
    //   const tr = document.createElement("tr");
    //   const id = document.createElement("td");
    //   const title = document.createElement("td");
    //   const writer = document.createElement("td");
    //   const dTime = document.createElement("td");
    //   // 클래스이름 생성

    //   // 태그속성추가
    //   id.textContent = (page - 1) * itemsPerPage + index + 1;
    //   title.textContent = d.title;
    //   writer.textContent = d.author.userId;
    //   dTime.textContent = formatPurchaseDate(d.modifiedDate);
    //   // appendChild 부모자식 위치 설정
    //   tr.appendChild(id);
    //   tr.appendChild(title);
    //   tr.appendChild(writer);
    //   tr.appendChild(dTime);
    //   tbody.appendChild(tr);

    //   tr.addEventListener("click", () => {
    //     displayBoardText(d, id.textContent, page);
    //   });
    // });
  }
}

function displayBoardText(data, idValue, pageNum) {
  const div = document.querySelector(".item-text-head");
  document.querySelector(".item-text").classList.remove("hidden");
  document.querySelector(".item-head").classList.add("hidden");
  div.innerHTML = "";
  // 태그 요소 생성
  const id = document.createElement("div");
  const title = document.createElement("div");
  const writer = document.createElement("div");
  const dTime = document.createElement("div");
  // 클래스이름 생성

  // 태그속성추가
  id.textContent = idValue;
  title.textContent = data.title;
  writer.textContent = data.author.userId;
  dTime.textContent = formatPurchaseDate(data.modifiedDate);
  // appendChild 부모자식 위치 설정
  div.appendChild(id);
  div.appendChild(title);
  div.appendChild(writer);
  div.appendChild(dTime);
  // 본문
  const content = document.querySelector(".item-text-body");
  content.style.height = `${50 * (itemsPerPage - 1)}px`;
  content.innerHTML = data.content;
}

function formatPurchaseDate(purchaseTime) {
  const date = new Date(purchaseTime);

  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0"); // 월은 0부터 시작하므로 1을 더함
  const day = String(date.getDate()).padStart(2, "0");

  const hours = String(date.getHours()).padStart(2, "0");
  const minutes = String(date.getMinutes()).padStart(2, "0");
  const seconds = String(date.getSeconds()).padStart(2, "0");

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

function displayPageNum() {
  const pages = document.querySelector(".board-page-num");
  pages.innerHTML = "";
  for (let i = 0; i < pageEnd; i++) {
    const num = document.createElement("div");
    num.classList.add("num-box");
    num.textContent = i + 1;
    pages.appendChild(num);
    num.addEventListener("click", () => {
      displayBoardHead(i + 1);
      pageCurrent = i + 1;
      changePageColor();
    });
  }
  changePageColor();
}

function changePageColor() {
  const boxes = document.querySelectorAll(".num-box");
  console.log(boxes);
  boxes.forEach((b, i) => {
    if (i == pageCurrent - 1) {
      b.style.backgroundColor = "#fec009";
    } else {
      b.style.backgroundColor = "#ffffff";
    }
  });
}

document.querySelector(".board-page-left").addEventListener("click", () => {
  pageCurrent = pageCurrent > 1 ? pageCurrent - 1 : pageCurrent;
  console.log(pageCurrent);
  changePageColor();
  displayBoardHead(pageCurrent);
});
document.querySelector(".board-page-right").addEventListener("click", () => {
  pageCurrent = pageCurrent < pageEnd ? pageCurrent + 1 : pageCurrent;
  console.log(pageCurrent);
  changePageColor();
  displayBoardHead(pageCurrent);
});
document.querySelector(".item-text-back-btn").addEventListener("click", () => {
  displayBoardHead(pageCurrent);
});

getBoard();
