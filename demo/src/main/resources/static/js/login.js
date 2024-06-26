const server_host = "http://demo-app-env.eba-sa6mjj47.ap-northeast-2.elasticbeanstalk.com";
const urlLogin = server_host+"/user/login";
const urlLogout = server_host+"/user/logout";
const urlSignup = server_host+"/user/signup";
const urlSession = server_host+"/user/current";
let userId = "";
let password = "";
let userIdSignup = "";
let passwordSignup = "";
let userName = "";
let userEmail = "";

document.querySelector("#userId").addEventListener("change", (e)=>{
  console.log(e.target.value);
  userId = e.target.value;
});
document.querySelector("#password").addEventListener("change", (e)=>{
  console.log(e.target.value);
  password = e.target.value;
});
document.querySelector("#userIdSignup").addEventListener("change", (e)=>{
  console.log(e.target.value);
  userIdSignup = e.target.value;
});
document.querySelector("#passwordSignup").addEventListener("change", (e)=>{
  console.log(e.target.value);
  passwordSignup = e.target.value;
});
document.querySelector("#userName").addEventListener("change", (e)=>{
  console.log(e.target.value);
  userName = e.target.value;
});
document.querySelector("#userEmail").addEventListener("change", (e)=>{
  console.log(e.target.value);
  userEmail = e.target.value;
});

document.querySelector(".loginBtn").addEventListener("click", ()=>{
  const data = {
    userId: userId,
    password: password,
  }
  axios
  .post(urlLogin, data, {withCredentials: true})
  .then((response)=>{
    console.log("데이터: ", response);
    sessionCurrent();
  })
  .catch((error)=>{
    console.log("에러 발생: ", error);
  })
});
document.querySelector(".logoutBtn").addEventListener("click", ()=>{
  if (confirm("로그아웃하시겠습니까?")) {
    axios
    .post(urlLogout, {}, {withCredentials: true})
    .then((response)=>{
      console.log("데이터:", response);
      if(response.status == 200) {
        document.querySelector(".login-box").classList.remove("hidden");
        document.querySelector(".user-box").classList.add("hidden");
      }
    })
    .catch((error)=>{
      console.log("에러 발생:", error);
    })
  }
})
document.querySelector(".signupBtn").addEventListener("click", ()=>{
  const data = {
    userId: userIdSignup,
    password: passwordSignup,
    userName: userName,
    userEmail: userEmail,
  }
  axios
  .post(urlSignup, data, {withCredentials: true})
  .then((response)=>{
    console.log("데이터: ", response);
    alert("회원가입이 완료되었습니다. 로그인해주세요.");
    window.location.reload();
  })
  .catch((error)=>{
    console.log("에러 발생: ", error);
  })
})

function signup() {
  document.querySelector(".login-box").classList.add("hidden");
  document.querySelector(".user-box").classList.add("hidden");
  document.querySelector(".signup-box").classList.remove("hidden");
}

function sessionCurrent() {
  axios
  .get(urlSession, {withCredentials: true})
  .then((response)=>{
    console.log("데이터:", response);
    if (response.status == 200) {
      console.log("세션 유지");
      document.querySelector(".login-box").classList.add("hidden");
      document.querySelector(".user-box").classList.remove("hidden");
      document.querySelector(".user-box p").textContent
        = response.data.userId + "님, 환영합니다.";
    }
  })
  .catch((error)=>{
    console.log("에러 발생:", error);
  })
}

// js 파일이 로드될때 호출됨
sessionCurrent();