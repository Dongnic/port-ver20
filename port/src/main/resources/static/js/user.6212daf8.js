"use strict";(self["webpackChunkfront"]=self["webpackChunkfront"]||[]).push([[378],{282:function(n,o,t){t.r(o),t.d(o,{default:function(){return g}});var s=t(6252);const i=(0,s._)("h1",null,"회원 가입 완료",-1),a=(0,s._)("hr",null,null,-1);function e(n,o){const t=(0,s.up)("router-link");return(0,s.wg)(),(0,s.iD)("div",null,[i,a,(0,s.Wm)(t,{to:"/loginForm"},{default:(0,s.w5)((()=>[(0,s.Uk)("로그인 하러가기")])),_:1})])}var l=t(3744);const r={},u=(0,l.Z)(r,[["render",e]]);var g=u},2432:function(n,o,t){t.r(o),t.d(o,{default:function(){return D}});var s=t(6252),i=t(9963);const a=n=>((0,s.dD)("data-v-2e16987f"),n=n(),(0,s.Cn)(),n),e={class:"login-signup"},l=a((()=>(0,s._)("h2",null,"Join",-1))),r=a((()=>(0,s._)("hr",null,null,-1))),u=a((()=>(0,s._)("br",null,null,-1))),g={key:0,class:"green password-message"},p={key:1,class:"red password-not"},d={key:2,class:"green password-check-message"},c={key:3,class:"red password-check-message"},h=a((()=>(0,s._)("button",{type:"submit"},"회원가입",-1)));function m(n,o,t,a,m,w){return(0,s.wg)(),(0,s.iD)("div",e,[l,r,u,(0,s._)("form",{onSubmit:o[8]||(o[8]=(0,i.iM)((n=>w.onSubmit()),["prevent"]))},[(0,s.wy)((0,s._)("input",{type:"email","onUpdate:modelValue":o[0]||(o[0]=o=>n.signup.email=o),placeholder:"이메일 입력",minlength:"1",required:""},null,512),[[i.nr,n.signup.email,void 0,{trim:!0}]]),(0,s.wy)((0,s._)("input",{type:"text","onUpdate:modelValue":o[1]||(o[1]=o=>n.signup.username=o),placeholder:"닉네임 입력",minlength:"1",maxlength:"20"},null,512),[[i.nr,n.signup.username,void 0,{trim:!0}]]),(0,s.wy)((0,s._)("input",{type:"password","onUpdate:modelValue":o[2]||(o[2]=o=>n.signup.password=o),placeholder:"비밀번호 입력",minlength:"4",maxlength:"16",onKeyup:o[3]||(o[3]=n=>w.passwordValid()),onBlur:o[4]||(o[4]=n=>w.passwordCheckValid()),autocomplete:"new-password"},null,544),[[i.nr,n.signup.password,void 0,{trim:!0}]]),2==n.passwordValidFlag?((0,s.wg)(),(0,s.iD)("span",g," 사용가능 ")):(0,s.kq)("",!0),n.passwordValidFlag?(0,s.kq)("",!0):((0,s.wg)(),(0,s.iD)("div",p," 영어 소문자+숫자 조합 4~16자리 입력해주세요 ")),(0,s.wy)((0,s._)("input",{type:"password","onUpdate:modelValue":o[5]||(o[5]=o=>n.passwordCheck=o),placeholder:"비밀번호 확인",minlength:"4",maxlength:"16",onKeyup:o[6]||(o[6]=n=>w.passwordCheckValid()),onBlur:o[7]||(o[7]=n=>w.passwordCheckValid()),autocomplete:"new-password"},null,544),[[i.nr,n.passwordCheck,void 0,{trim:!0}]]),2==n.passwordCheckFlag?((0,s.wg)(),(0,s.iD)("span",d," 일치 ")):(0,s.kq)("",!0),n.passwordCheckFlag?(0,s.kq)("",!0):((0,s.wg)(),(0,s.iD)("div",c," 불일치 ")),h],32)])}t(7658);var w=t(4206),v=t.n(w),k={name:"JoinPage",props:{},data:()=>({signup:{email:"",username:"",password:""},passwordCheck:"",passwordValidFlag:1,passwordCheckFlag:1}),methods:{onSubmit:function(){if(console.log(this.signup),null==this.signup.email||null==this.signup.username||null==this.signup.password)return void alert("필수값 누락");if(!this.passwordValidFlag||!this.passwordCheckFlag)return void alert("유효성 확인");const n=this.signup,o=this;v().post("/user",n).then((function(n){console.log("data : ",n.data),console.log("data type : ",typeof n.data),alert(n.data),o.$router.push("/joinDone")})).catch((function(n){console.log(n)}))},passwordValid(){/^(?=.*[a-z])(?=.*[0-9]).{4,16}$/.test(this.signup.password)?this.passwordValidFlag=2:this.passwordValidFlag=0},passwordCheckValid(){this.signup.password===this.passwordCheck?this.passwordCheckFlag=2:this.passwordCheckFlag=0}}},f=t(3744);const b=(0,f.Z)(k,[["render",m],["__scopeId","data-v-2e16987f"]]);var D=b},515:function(n,o,t){t.r(o),t.d(o,{default:function(){return T}});var s=t(6252),i=t(3577),a=t(9963);const e={class:"login-container"},l={class:"login-backbox"};function r(n,o,t,r,u,g){const p=(0,s.up)("LoginMsg"),d=(0,s.up)("Login"),c=(0,s.up)("Join");return(0,s.wg)(),(0,s.iD)("div",e,[(0,s._)("div",l,[(0,s.Wm)(p,{isLogin:u.isLogin,onSwitchLogin:g.switchLogin},null,8,["isLogin","onSwitchLogin"])]),(0,s._)("div",{class:(0,i.C_)(["login-frontbox",{moving:!u.isLogin}])},[(0,s.wy)((0,s.Wm)(d,null,null,512),[[a.F8,u.isLogin]]),(0,s.wy)((0,s.Wm)(c,null,null,512),[[a.F8,!u.isLogin]])],2)])}var u=t(1860),g=t(2432);const p=n=>((0,s.dD)("data-v-176e37f8"),n=n(),(0,s.Cn)(),n),d={class:"login-Msg"},c={class:"login-loginMsg"},h=p((()=>(0,s._)("p",{class:"login-title"},"Don't have an account?",-1))),m=p((()=>(0,s._)("p",null,"Sign up to save all your graph.",-1))),w={class:"login-textconten login-textcontent-signup"},v=p((()=>(0,s._)("p",{class:"login-title"},"Have an account?",-1))),k=p((()=>(0,s._)("p",null,"Log in to see all your collection.",-1)));function f(n,o,t,a,e,l){return(0,s.wg)(),(0,s.iD)("div",d,[(0,s._)("div",c,[(0,s._)("div",{class:(0,i.C_)(["login-textcontent",{visibility:!t.isLogin}])},[h,m,(0,s._)("button",{id:"login-switch1",class:"login-button",onClick:o[0]||(o[0]=(...n)=>l.toJoin&&l.toJoin(...n))},"Sign Up")],2)]),(0,s._)("div",{class:(0,i.C_)(["login-signupMsg",{visibility:t.isLogin}])},[(0,s._)("div",w,[v,k,(0,s._)("button",{id:"login-switch2",class:"login-button",onClick:o[1]||(o[1]=(...n)=>l.toLogin&&l.toLogin(...n))},"LOG IN")])],2)])}var b={name:"LoginMsg",props:{isLogin:Boolean},methods:{toLogin(){console.log("toLogin"),this.$emit("switchLogin",!0)},toJoin(){console.log("toJoin"),this.$emit("switchLogin",!1)}}},D=t(3744);const L=(0,D.Z)(b,[["render",f],["__scopeId","data-v-176e37f8"]]);var y=L,U={name:"LoginJoin",components:{LoginMsg:y,Login:u["default"],Join:g["default"]},data(){return{isLogin:!0}},methods:{switchLogin(n){this.isLogin=n}}};const V=(0,D.Z)(U,[["render",r],["__scopeId","data-v-7b29cda8"]]);var T=V},1860:function(n,o,t){t.r(o),t.d(o,{default:function(){return L}});var s=t(6252),i=t(9963);const a=n=>((0,s.dD)("data-v-c86b08d0"),n=n(),(0,s.Cn)(),n),e={class:"login-login"},l=a((()=>(0,s._)("h2",null,"Login",-1))),r=a((()=>(0,s._)("hr",null,null,-1))),u=a((()=>(0,s._)("br",null,null,-1))),g=a((()=>(0,s._)("button",{type:"submit"},"로그인",-1))),p=a((()=>(0,s._)("br",null,null,-1))),d=a((()=>(0,s._)("a",{href:"/oauth2/authorization/google?redirect_uri=http://localhost:8080/PortChat"},[(0,s._)("img",{class:"google login-Logo",src:"data:image/png;base64,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"})],-1))),c=a((()=>(0,s._)("a",{href:"/oauth2/authorization/github?redirect_uri=http://192.168.0.20:8080/PortChat"},[(0,s._)("img",{class:"github login-Logo",src:"https://w7.pngwing.com/pngs/490/316/png-transparent-computer-icons-github-github-icon-cat-like-mammal-carnivoran-black.png"})],-1))),h=a((()=>(0,s._)("a",{href:"/oauth2/authorization/naver?redirect_uri=http://192.168.0.20:8080/PortChat"},[(0,s._)("img",{class:"naver login-Logo",src:"https://t1.daumcdn.net/cfile/tistory/994331335F52047D05"})],-1))),m=a((()=>(0,s._)("a",{href:"/oauth2/authorization/kakao?redirect_uri=http://192.168.0.20:8080/PortChat"},[(0,s._)("img",{class:"kakao login-Logo",src:"https://www.gotchawashmall.com/design/gotcha82/gotcha/img/kakao.png"})],-1)));function w(n,o,t,a,w,v){return(0,s.wg)(),(0,s.iD)("div",e,[l,r,u,(0,s._)("form",{onSubmit:o[2]||(o[2]=(0,i.iM)((n=>v.onSubmit()),["prevent"]))},[(0,s.wy)((0,s._)("input",{type:"email",name:"username","onUpdate:modelValue":o[0]||(o[0]=o=>n.username=o),placeholder:"이메일 입력",minlength:"1",required:""},null,512),[[i.nr,n.username,void 0,{trim:!0}]]),(0,s.wy)((0,s._)("input",{type:"password",name:"password","onUpdate:modelValue":o[1]||(o[1]=o=>n.password=o),placeholder:"비밀번호 입력",minlength:"4",maxlength:"16",autocomplete:"new-password",required:""},null,512),[[i.nr,n.password,void 0,{trim:!0}]]),g],32),p,d,(0,s.Uk)("   "),c,(0,s.Uk)("   "),h,(0,s.Uk)("   "),m])}var v=t(4206),k=t.n(v),f={name:"LoginPage",props:{},data:()=>({username:"",password:""}),methods:{onSubmit:function(){if(null==this.username||null==this.password)return void alert("필수값 누락");const n={username:this.username,password:this.password};console.log(n),k().post("/login",n).then((function(n){alert("data",n.data),console.log("data : ",n.data),console.log("data type : ",typeof n.data)})).catch((function(n){console.log(n);const o=decodeURIComponent(n.request.responseURL),t=o.split("="),s=t[1].replaceAll("+"," ");alert(s)}))}}},b=t(3744);const D=(0,b.Z)(f,[["render",w],["__scopeId","data-v-c86b08d0"]]);var L=D}}]);
//# sourceMappingURL=user.6212daf8.js.map