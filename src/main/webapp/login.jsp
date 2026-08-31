<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Sunrise Dental Clinic</title>

    <link rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/assets/css/login/login.css">
</head>

<body>

    <div class="login-page">

        <div class="login-card">

            <!-- Clinic title -->
            <div class="login-header">
                <h1>
                    <span class="title-dark">Sunrise</span>
                    <span class="title-blue"> Dental Clinic</span>
                </h1>

                <div class="title-line">
                    <span></span>
                    <i></i>
                </div>
            </div>


            <% if (request.getParameter("error") != null) { %>
                <div class="error">
                    Invalid username or password
                </div>
            <% } %>


            <form id="loginForm"
                  action="${pageContext.request.contextPath}/login"
                  method="post"
                  novalidate>

                <!-- Username -->
                <div class="form-group">

                    <label for="username">Username</label>

                    <div class="input-wrapper">

                        <span class="input-icon user-icon">
                            <svg viewBox="0 0 24 24"
                                 xmlns="http://www.w3.org/2000/svg">
                                <circle cx="12" cy="8" r="4"></circle>
                                <path d="M4 21c0-4 3.5-7 8-7s8 3 8 7"></path>
                            </svg>
                        </span>

                        <input type="text"
                               id="username"
                               name="username"
                               placeholder="Enter username">

                    </div>

                    <div id="usernameError" class="field-error"></div>

                </div>


                <!-- Password -->
                <div class="form-group">

                    <label for="password">Password</label>

                    <div class="input-wrapper">

                        <span class="input-icon lock-icon">
                            <svg viewBox="0 0 24 24"
                                 xmlns="http://www.w3.org/2000/svg">
                                <rect x="5" y="10" width="14" height="10" rx="2"></rect>
                                <path d="M8 10V7a4 4 0 0 1 8 0v3"></path>
                            </svg>
                        </span>

                        <input type="password"
                               id="password"
                               name="password"
                               placeholder="Enter password">

                    </div>

                    <div id="passwordError" class="field-error"></div>

                </div>


                <!-- Login button -->
                <button type="submit" class="login-button">
                    Login
                </button>

            </form>


            <!-- Footer -->
            <div class="login-footer">
                © 2026 Sunrise Dental Clinic. All rights reserved.
            </div>

        </div>

    </div>


    <script src="${pageContext.request.contextPath}/assets/js/login/login.js"></script>

</body>
</html>