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

// 페이지 로딩시에 즉시 세션여부 확인
sessionCurrent();