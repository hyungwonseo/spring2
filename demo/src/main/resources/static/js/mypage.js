const server_host = "http://demo-app-env.eba-sa6mjj47.ap-northeast-2.elasticbeanstalk.com";
const urlPurchaseAll = server_host+"/api/products/purchase";
const urlPurchaseById = server_host+"/api/products/purchase/id/";
const urlPurchaseByCurrent = server_host+"/api/products/purchase/current";
const urlSession = server_host+"/user/current";

const adminPage = document.querySelector(".admin_page");
const userPage = document.querySelector(".user_page");

function sessionCurrent() {
  axios
  .get(urlSession, {withCredentials: true})
  .then((response)=>{
    console.log("데이터:", response);
    if (response.status == 200) {
      console.log("세션 유지");
      const userId = response.data.userId;
      const authority = response.data.authority[0].authority;
      if (authority == "ROLE_ADMIN") {
        adminPage.classList.remove("hidden");
        userPage.classList.add("hidden");
      }else if (authority == "ROLE_USER") {
        adminPage.classList.add("hidden");
        userPage.classList.remove("hidden");
        axios
        .get(urlPurchaseByCurrent, {withCredentials: true})
        .then((response)=>{
          console.log("데이터:", response);
          displayPurchaseInfo(response.data);
        })
        .catch((error)=>{
          console.log("에러 발생:", error);
        })
      }else {
        console.log("에러! 여기오면 안되는데..")
      }

      document.querySelector(".pageSubmitBtn").addEventListener("click", ()=>{
        const dropdown = document.querySelector('#dropdown');
        const selectedUserId = document.querySelector("#userIdInput").value;
        let url = "";
        if (dropdown.value == "userId") {
          if (selectedUserId == "" || selectedUserId == null) {
            alert("유저 아이디를 입력해주세요.");
            return;
          }else {
            url = urlPurchaseById + selectedUserId;
          }          
        }else {
          url = urlPurchaseAll;
        }
        axios
        .get(url, {withCredentials: true})
        .then((response)=>{
          console.log("데이터:", response);
          displayPurchaseInfo(response.data);
        })
        .catch((error)=>{
          console.log("에러 발생:", error);
          alert("입력하신 유저 아이디는 존재하지 않습니다.");
        })
      })
    }
  })
  .catch((error)=>{
    console.log("에러 발생:", error);
    alert("로그인해주세요.");
  })
}

document.addEventListener('DOMContentLoaded', (event) => {
  const dropdown = document.querySelector('#dropdown');
  dropdown.addEventListener('change', (event) => {
    document.querySelector("#userIdInput").value = "";
  });
});

function displayPurchaseInfo(games) {
  const table = document.querySelector(".purchase-table");
  const tbody = document.querySelector(".purchase-body");
  // 테이블 바디 초기화
  tbody.innerHTML = "";
  games.forEach((data, index)=>{
    // 태그 요소 생성
    const tr = document.createElement("tr");
    const num = document.createElement("td");
    const gameId = document.createElement("td");
    const title = document.createElement("td");
    const userId = document.createElement("td");
    const date = document.createElement("td");
    // 클래스이름 생성

    // 태그속성추가
    num.textContent = index + 1;
    gameId.textContent = data.game.id;
    title.textContent = data.game.title;
    userId.textContent = data.user.userId;
    date.textContent = formatPurchaseDate(data.purchaseTime);
    // appendChild 부모자식 위치 설정
    tr.appendChild(num);
    tr.appendChild(gameId);
    tr.appendChild(title);
    tr.appendChild(userId);
    tr.appendChild(date);
    tbody.appendChild(tr);
    if (adminPage.classList.contains("hidden")) {
      userPage.appendChild(table);
    } else {
      adminPage.appendChild(table);
    }
  })
}

function formatPurchaseDate(purchaseTime) {
  const date = new Date(purchaseTime);

  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더함
  const day = String(date.getDate()).padStart(2, '0');

  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

sessionCurrent();