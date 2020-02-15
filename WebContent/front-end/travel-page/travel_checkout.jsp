<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="java.util.*"%> 
<%@ page import="com.tour.model.*"%> 
<% 
TourVO tourVO = (TourVO) request.getAttribute("tourVO"); 
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!-- Bootstrap CSS -->
    <link
      rel="stylesheet"
      href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
    />
    <!-- jQuery -->
    <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
    
    <link
      href="<%=request.getContextPath()%>/css/dive.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="<%=request.getContextPath()%>/css/creditcard.css"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="<%=request.getContextPath()%>/css/travel_checkout.css"
      rel="stylesheet"
      type="text/css"
    />

    <link href="<%=request.getContextPath()%>/images/twdivers.png" rel="icon" />
    <title>潛進台灣Taiwan Divers</title>
  </head>
  <body>
    <div class="container-fluid checkoutBg" style="min-height: 100%;">
      <div class="multisteps-form">
        <!--progress bar-->
        <div class="row mt-5">
          <div class="col-12 col-lg-8 ml-auto mr-auto mb-4">
            <div class="multisteps-form__progress">
              <button
                class="multisteps-form__progress-btn js-active"
                type="button"
                title="User Info"
              >
                	確認訂單
              </button>
              <button
                class="multisteps-form__progress-btn"
                type="button"
                title="Address"
              >
               	 進行付款
              </button>
              <button
                class="multisteps-form__progress-btn"
                type="button"
                title="finish"
              >
               	 完成付款
              </button>
            </div>
          </div>
        </div>
        <!--form panels-->
        <div class="row mb-4">
          <div class="col-12 col-lg-8 m-auto">
            <form class="multisteps-form__form">
              <!--single form panel-->
              <div
                class="multisteps-form__panel shadow p-4 rounded bg-white js-active"
                data-animation="scaleIn"
              >
                <h3 class="multisteps-form__title">確認訂單</h3>
                <div class="multisteps-form__content">
                  <div class="form-row mt-4 mb-4">
                    <div class="col-12 col-sm-12 mb-2">
                      <img
                        src="<%=request.getContextPath()%>/images/twdivers.png"
                        class="checkoutPic"
                      />
                    </div>
                    <div class="col-12 col-sm-12 mb-2">
                      <span class="multisteps-form__input"
                        >行程名稱: ${sessionScope.tourVO.tour_name}</span
                      >
                    </div>
                    <div class="col-12 col-sm-12 mt-sm-0 mb-2">
                      <span class="multisteps-form__input"
                        >地點: ${sessionScope.tourVO.tour_place}</span
                      >
                    </div>
                    <div class="col-12 col-sm-12 mt-sm-0 mb-2">
                      <span class="multisteps-form__input"
                        >出發日期: ${sessionScope.tourVO.tour_bgn_date}</span
                      >
                    </div>
                    <div class="col-12 col-sm-12 mt-sm-0 mb-2">
                      <span class="multisteps-form__input"
                        >回程日期: ${sessionScope.tourVO.tour_end_date}</span
                      >
                    </div>
                    <div class="col-12 col-sm-12 mt-sm-0 mb-2">
                      <span class="multisteps-form__input" name="tour_price" value="${tourVO.tour_price}"
                        >價格: ${sessionScope.tourVO.tour_price}</span
                      >
                    </div>
                    <div class="col-12 col-sm-12 mt-sm-0">
                      <span class="multisteps-form__input"
                        >潛水支數: ${sessionScope.tourVO.tour_dives}</span
                      >
                    </div>
                  </div>

                  <div class="button-row d-flex">
                  
			<!--以下修改 -->
				<a href="<%=request.getContextPath()%>/front-end/travel-page/travel.jsp">
                  	<input type="hidden" name="action" value="removeSession">
                    <button id="reBuy" class="btn reBuy" type="button">重新購買</button>
                </a>
			<!-- 以上修改 -->
                  
                    <button class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">下一步</button>
                  </div>
                </div>
              </div>
              <!--single form panel-->
              <div
                class="multisteps-form__panel shadow p-4 rounded bg-white"
                data-animation="scaleIn"
              >
                <h3 class="multisteps-form__title">進行付款</h3>
                <div class="multisteps-form__content">
                  <div class="row">
                    <div class="container preload">
                      <div class="creditcard">
                        <div class="front">
                          <div id="ccsingle"></div>
                          <svg
                            version="1.1"
                            id="cardfront"
                            xmlns="http://www.w3.org/2000/svg"
                            xmlns:xlink="http://www.w3.org/1999/xlink"
                            x="0px"
                            y="0px"
                            viewBox="0 0 750 471"
                            style="enable-background: new 0 0 750 471;"
                            xml:space="preserve"
                          >
                            <g id="Front">
                              <g id="CardBackground">
                                <g id="Page-1_1_">
                                  <g id="amex_1_">
                                    <path
                                      id="Rectangle-1_1_"
                                      class="lightcolor grey"
                                      d="M40,0h670c22.1,0,40,17.9,40,40v391c0,22.1-17.9,40-40,40H40c-22.1,0-40-17.9-40-40V40C0,17.9,17.9,0,40,0z"
                                    />
                                  </g>
                                </g>
                                <path
                                  class="darkcolor greydark"
                                  d="M750,431V193.2c-217.6-57.5-556.4-13.5-750,24.9V431c0,22.1,17.9,40,40,40h670C732.1,471,750,453.1,750,431z"
                                />
                              </g>
                              <text
                                transform="matrix(1 0 0 1 60.106 295.0121)"
                                id="svgnumber"
                                class="st2 st3 st4"
                              >
                           0000 0000 0000 0000
                              </text>
                              <text
                                transform="matrix(1 0 0 1 54.1064 428.1723)"
                                id="svgname"
                                class="st2 st5 st6"
                              >
                                John Doe
                              </text>
                              <text
                                transform="matrix(1 0 0 1 54.1074 389.8793)"
                                class="st7 st5 st8"
                              >
                                cardholder name
                              </text>
                              <text
                                transform="matrix(1 0 0 1 479.7754 388.8793)"
                                class="st7 st5 st8"
                              >
                                expiration
                              </text>
                              <text
                                transform="matrix(1 0 0 1 65.1054 241.5)"
                                class="st7 st5 st8"
                              >
                                card number
                              </text>
                              <g>
                                <text
                                  transform="matrix(1 0 0 1 574.4219 433.8095)"
                                  id="svgexpire"
                                  class="st2 st5 st9"
                                >
                                  01/23
                                </text>
                                <text
                                  transform="matrix(1 0 0 1 479.3848 417.0097)"
                                  class="st2 st10 st11"
                                >
                                  VALID
                                </text>
                                <text
                                  transform="matrix(1 0 0 1 479.3848 435.6762)"
                                  class="st2 st10 st11"
                                >
                                  THRU
                                </text>
                                <polygon
                                  class="st2"
                                  points="554.5,421 540.4,414.2 540.4,427.9"
                                />
                              </g>
                              <g id="cchip">
                                <g>
                                  <path
                                    class="st2"
                                    d="M168.1,143.6H82.9c-10.2,0-18.5-8.3-18.5-18.5V74.9c0-10.2,8.3-18.5,18.5-18.5h85.3
                        c10.2,0,18.5,8.3,18.5,18.5v50.2C186.6,135.3,178.3,143.6,168.1,143.6z"
                                  />
                                </g>
                                <g>
                                  <g>
                                    <rect
                                      x="82"
                                      y="70"
                                      class="st12"
                                      width="1.5"
                                      height="60"
                                    />
                                  </g>
                                  <g>
                                    <rect
                                      x="167.4"
                                      y="70"
                                      class="st12"
                                      width="1.5"
                                      height="60"
                                    />
                                  </g>
                                  <g>
                                    <path
                                      class="st12"
                                      d="M125.5,130.8c-10.2,0-18.5-8.3-18.5-18.5c0-4.6,1.7-8.9,4.7-12.3c-3-3.4-4.7-7.7-4.7-12.3
			                            c0-10.2,8.3-18.5,18.5-18.5s18.5,8.3,18.5,18.5c0,4.6-1.7,8.9-4.7,12.3c3,3.4,4.7,7.7,4.7,12.3
			                             C143.9,122.5,135.7,130.8,125.5,130.8z M125.5,70.8c-9.3,0-16.9,7.6-16.9,16.9c0,4.4,1.7,8.6,4.8,11.8l0.5,0.5l-0.5,0.5
			                             c-3.1,3.2-4.8,7.4-4.8,11.8c0,9.3,7.6,16.9,16.9,16.9s16.9-7.6,16.9-16.9c0-4.4-1.7-8.6-4.8-11.8l-0.5-0.5l0.5-0.5
			                             c3.1-3.2,4.8-7.4,4.8-11.8C142.4,78.4,134.8,70.8,125.5,70.8z"
                                    />
                                  </g>
                                  <g>
                                    <rect
                                      x="82.8"
                                      y="82.1"
                                      class="st12"
                                      width="25.8"
                                      height="1.5"
                                    />
                                  </g>
                                  <g>
                                    <rect
                                      x="82.8"
                                      y="117.9"
                                      class="st12"
                                      width="26.1"
                                      height="1.5"
                                    />
                                  </g>
                                  <g>
                                    <rect
                                      x="142.4"
                                      y="82.1"
                                      class="st12"
                                      width="25.8"
                                      height="1.5"
                                    />
                                  </g>
                                  <g>
                                    <rect
                                      x="142"
                                      y="117.9"
                                      class="st12"
                                      width="26.2"
                                      height="1.5"
                                    />
                                  </g>
                                </g>
                              </g>
                            </g>
                            <g id="Back"></g>
                          </svg>
                        </div>
                        <div class="back">
                          <svg
                            version="1.1"
                            id="cardback"
                            xmlns="http://www.w3.org/2000/svg"
                            xmlns:xlink="http://www.w3.org/1999/xlink"
                            x="0px"
                            y="0px"
                            viewBox="0 0 750 471"
                            style="enable-background: new 0 0 750 471;"
                            xml:space="preserve"
                          >
                            <g id="Front">
                              <line
                                class="st0"
                                x1="35.3"
                                y1="10.4"
                                x2="36.7"
                                y2="11"
                              />
                            </g>
                            <g id="Back">
                              <g id="Page-1_2_">
                                <g id="amex_2_">
                                  <path
                                    id="Rectangle-1_2_"
                                    class="darkcolor greydark"
                                    d="M40,0h670c22.1,0,40,17.9,40,40v391c0,22.1-17.9,40-40,40H40c-22.1,0-40-17.9-40-40V40
                         C0,17.9,17.9,0,40,0z"
                                  />
                                </g>
                              </g>
                              <rect
                                y="61.6"
                                class="st2"
                                width="750"
                                height="78"
                              />
                              <g>
                                <path
                                  class="st3"
                                  d="M701.1,249.1H48.9c-3.3,0-6-2.7-6-6v-52.5c0-3.3,2.7-6,6-6h652.1c3.3,0,6,2.7,6,6v52.5
                     C707.1,246.4,704.4,249.1,701.1,249.1z"
                                />
                                <rect
                                  x="42.9"
                                  y="198.6"
                                  class="st4"
                                  width="664.1"
                                  height="10.5"
                                />
                                <rect
                                  x="42.9"
                                  y="224.5"
                                  class="st4"
                                  width="664.1"
                                  height="10.5"
                                />
                                <path
                                  class="st5"
                                  d="M701.1,184.6H618h-8h-10v64.5h10h8h83.1c3.3,0,6-2.7,6-6v-52.5C707.1,187.3,704.4,184.6,701.1,184.6z"
                                />
                              </g>
                              <text
                                transform="matrix(1 0 0 1 621.999 227.2734)"
                                id="svgsecurity"
                                class="st6 st7"
                              >
                                985
                              </text>
                              <g class="st8">
                                <text
                                  transform="matrix(1 0 0 1 518.083 280.0879)"
                                  class="st9 st6 st10"
                                >
                                  security code
                                </text>
                              </g>
                              <rect
                                x="58.1"
                                y="378.6"
                                class="st11"
                                width="375.5"
                                height="13.5"
                              />
                              <rect
                                x="58.1"
                                y="405.6"
                                class="st11"
                                width="421.7"
                                height="13.5"
                              />
                              <text
                                transform="matrix(1 0 0 1 59.5073 228.6099)"
                                id="svgnameback"
                                class="st12 st13"
                              >
                                John Doe
                              </text>
                            </g>
                          </svg>
                        </div>
                      </div>
                    </div>
                    <div class="form-container">
                      <div class="field-container">
                        <label for="name">Name</label>
                        <input id="name" maxlength="20" type="text" required/>
                      </div>
                      <div class="field-container">
                        <label for="cardnumber">Card Number</label>
                        <input
                          id="cardnumber"
                          type="text"
                          pattern="[0-9]*"
                          inputmode="numeric"
                          required
                        />
                        <svg
                          id="ccicon"
                          class="ccicon"
                          width="750"
                          height="471"
                          viewBox="0 0 750 471"
                          version="1.1"
                          xmlns="http://www.w3.org/2000/svg"
                          xmlns:xlink="http://www.w3.org/1999/xlink"
                        ></svg>
                      </div>
                      <div class="field-container">
                        <label for="expirationdate">Expiration (mm/yy)</label>
                        <input
                          id="expirationdate"
                          type="text"
                          pattern="[0-9]*"
                          inputmode="numeric"
                          required
                        />
                      </div>
                      <div class="field-container">
                        <label for="securitycode">Security Code</label>
                        <input
                          id="securitycode"
                          type="text"
                          pattern="[0-9]*"
                          inputmode="numeric"
                          required
                        />
                      </div>
                      <p id="pleaseInsert" class="mt-2" style="color: red;"></p>
                    </div>
                  </div>
                    <div class="button-row d-flex mt-4 col-12">
                      <button
                        class="btn btn-primary js-btn-prev"
                        type="button"
                        title="Prev">
                       	 上一步
                      </button>
	                  <button id="bookTour" class="btn btn-primary ml-auto js-btn-next" type="button" title="Next">
	                                                             確定付款
	                  </button>
                    </div>
                </div>
              </div>

<!--       single form panel -->
              <div class="multisteps-form__panel shadow p-4 rounded bg-white" data-animation="scaleIn" >
                <h3 class="multisteps-form__title">完成付款</h3>
                <div class="multisteps-form__content">
                  <div class="form-row mt-4 mb-4 ml-0">
                    <p>請至會員專區查看訂單資訊</p>
                  </div>

                  <div class="button-row d-flex">
                  <a href="<%=request.getContextPath()%>/front-end/travel-page/travel.jsp">
                  <button
                      class="btn btn-primary ml-auto js-btn-next"
                      type="button"
                      title="Next">
                      	完成
                    </button>
                    </a>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <footer style="width: 100%;">
		<div class="logo">
			<img src="<%=request.getContextPath()%>/images/twdivers.png"
				class="logo_img" alt="tw_logo">
		</div>
		<!-- 揪 行 裝 社 -->
		<ul class="nav justify-content-center" id="footer">
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/group/listAllGroup.jsp">潛點揪團</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/travel-page/travel.jsp">行程</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/equipment/equip_shop.jsp">裝備</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/album/albumList.jsp">社群</a></li>
		</ul>


      <!-- fake icon -->
      <ul class="nav justify-content-center" id="icon">
        <li class="nav-item">
          <svg id="fb" width="25px" height="25px" viewBox="0 0 96.124 96.123">
            <path
              d="M72.089,0.02L59.624,0C45.62,0,36.57,9.285,36.57,23.656v10.907H24.037c-1.083,0-1.96,0.878-1.96,1.961v15.803
				c0,1.083,0.878,1.96,1.96,1.96h12.533v39.876c0,1.083,0.877,1.96,1.96,1.96h16.352c1.083,0,1.96-0.878,1.96-1.96V54.287h14.654
				c1.083,0,1.96-0.877,1.96-1.96l0.006-15.803c0-0.52-0.207-1.018-0.574-1.386c-0.367-0.368-0.867-0.575-1.387-0.575H56.842v-9.246
				c0-4.444,1.059-6.7,6.848-6.7l8.397-0.003c1.082,0,1.959-0.878,1.959-1.96V1.98C74.046,0.899,73.17,0.022,72.089,0.02z"
            />
          </svg>
        </li>
        <li class="nav-item">
          <svg id="ig" width="25px" height="25px" viewBox="0 0 512 512">
            <path
              d="M352,0H160C71.648,0,0,71.648,0,160v192c0,88.352,71.648,160,160,160h192c88.352,0,160-71.648,160-160V160
				C512,71.648,440.352,0,352,0z M464,352c0,61.76-50.24,112-112,112H160c-61.76,0-112-50.24-112-112V160C48,98.24,98.24,48,160,48
				h192c61.76,0,112,50.24,112,112V352z"
            />
            <path
              d="M256,128c-70.688,0-128,57.312-128,128s57.312,128,128,128s128-57.312,128-128S326.688,128,256,128z M256,336
				c-44.096,0-80-35.904-80-80c0-44.128,35.904-80,80-80s80,35.872,80,80C336,300.096,300.096,336,256,336z"
            />
            <circle cx="393.6" cy="118.4" r="17.056" />
          </svg>
        </li>
        <li class="nav-item">
          <svg id="twit" width="25px" height="25px" viewBox="0 0 512 512">
            <path
              d="M512,97.248c-19.04,8.352-39.328,13.888-60.48,16.576c21.76-12.992,38.368-33.408,46.176-58.016
				c-20.288,12.096-42.688,20.64-66.56,25.408C411.872,60.704,384.416,48,354.464,48c-58.112,0-104.896,47.168-104.896,104.992
				c0,8.32,0.704,16.32,2.432,23.936c-87.264-4.256-164.48-46.08-216.352-109.792c-9.056,15.712-14.368,33.696-14.368,53.056
				c0,36.352,18.72,68.576,46.624,87.232c-16.864-0.32-33.408-5.216-47.424-12.928c0,0.32,0,0.736,0,1.152
				c0,51.008,36.384,93.376,84.096,103.136c-8.544,2.336-17.856,3.456-27.52,3.456c-6.72,0-13.504-0.384-19.872-1.792
				c13.6,41.568,52.192,72.128,98.08,73.12c-35.712,27.936-81.056,44.768-130.144,44.768c-8.608,0-16.864-0.384-25.12-1.44
				C46.496,446.88,101.6,464,161.024,464c193.152,0,298.752-160,298.752-298.688c0-4.64-0.16-9.12-0.384-13.568
				C480.224,136.96,497.728,118.496,512,97.248z"
            />
          </svg>
        </li>
        <li class="nav-item">
          <svg id="yt" width="25px" height="25px" viewBox="0 0 512 512">
            <path
              d="M490.24,113.92c-13.888-24.704-28.96-29.248-59.648-30.976C399.936,80.864,322.848,80,256.064,80
				c-66.912,0-144.032,0.864-174.656,2.912c-30.624,1.76-45.728,6.272-59.744,31.008C7.36,138.592,0,181.088,0,255.904
				C0,255.968,0,256,0,256c0,0.064,0,0.096,0,0.096v0.064c0,74.496,7.36,117.312,21.664,141.728
				c14.016,24.704,29.088,29.184,59.712,31.264C112.032,430.944,189.152,432,256.064,432c66.784,0,143.872-1.056,174.56-2.816
				c30.688-2.08,45.76-6.56,59.648-31.264C504.704,373.504,512,330.688,512,256.192c0,0,0-0.096,0-0.16c0,0,0-0.064,0-0.096
				C512,181.088,504.704,138.592,490.24,113.92z M192,352V160l160,96L192,352z"
            />
          </svg>
        </li>
      </ul>
    </footer>

    <!-- Optional JavaScript -->
	
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://unpkg.com/imask"></script>
    <script src="<%=request.getContextPath()%>/js/travel_checkout.js"></script>
	<script src="<%=request.getContextPath()%>/js/creditcard.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    
     
  </body>

  <script text="text/javascript">
  	$(document).ready(function(){
  		$("#reBuy").click(function(){
  			$.ajax({
	  				url: "<%=request.getContextPath()%>/tour/tour.do",
	  	  			type: "POST",
	  	  			data: {
	  	  				action: "removeSession"
	  	  			},

	  			});
  		})
  		
  		
  		$("#bookTour").click(function(){
  				$.ajax({
  	  				url: "<%=request.getContextPath()%>/tour_order/tourOrder.do",
  	  	  			type: "POST",
  	  	  			data: {
  	  	  				action: "bookTour",
  	  	  				tour_no: "${sessionScope.tourVO.tour_no}", //修改
  	  	  				mem_no: "${memVO.mem_no}",
  	  	  				tour_price: "${sessionScope.tourVO.tour_price}", //修改
  	  	  			},
  	  	  			
  	  	  			success: function(data){
  		  	  			Swal.fire(
  		  	  				  '付款成功',
  		  	  				  'success'
  		  	  				);
  	  	  			}
  	  			});

  		})
  		
  	})

  
  </script>
  
</html>
