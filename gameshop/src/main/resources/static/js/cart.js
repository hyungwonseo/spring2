const url = "http://localhost:8080/api/products/purchaselist";

function sessionCurrent() {
  axios
  .get("http://localhost:8080/user/current", {withCredentials: true})
  .then((response)=>{
    console.log("데이터:", response.data);
    if (response.status == 200) {
      const userId = response.data;
      let cartItems = JSON.parse(localStorage.getItem(userId));
      if (cartItems) {
        displayCart(cartItems);
        const data = cartItems.map((game)=>{
          // Purchase객체를 만들어서 리턴
          return { game: game, user:{userId:userId} };
        })
        document.querySelector(".purchaseBtn")
          .addEventListener("click", ()=>{
            if (confirm("구매하시겠습니까?")) {
              axios
              .post(url, data, {withCredentials: true})
              .then((response)=>{
                console.log("데이터:", response.data);
                localStorage.removeItem(userId);
              })
              .catch((error)=>{
                console.log("에러 발생:", error);
              });
            }
        });
      }
    }
  })
  .catch((error)=>{
    console.log("에러 발생:", error);
    alert("로그인해주세요.");
  })
}

function displayCart(games) {
  const tbody = document.querySelector(".cart-body");
  let totalPrice = 0;
  games.forEach((data)=>{
    // 태그 요소 생성
    const tr = document.createElement("tr");
    const imgtd = document.createElement("td");
    const img = document.createElement("img");
    const title = document.createElement("td");
    const genre = document.createElement("td");
    const price = document.createElement("td");
    // 클래스이름 생성
    imgtd.classList.add("imgtd");
    img.classList.add("image");
    // 태그속성추가
    img.src = data.image;
    title.textContent = data.title;
    genre.textContent = data.genre;
    price.textContent = data.price + "원";
    // appendChild 부모자식 위치 설정
    imgtd.appendChild(img);
    tr.appendChild(imgtd);
    tr.appendChild(title);
    tr.appendChild(genre);
    tr.appendChild(price);
    tbody.appendChild(tr);

    totalPrice = totalPrice + data.price;
  })
  document.querySelector(".totalprice").textContent = "총 " + totalPrice + "원";
}

// 페이지 로딩시에 즉시 세션여부 확인
sessionCurrent();