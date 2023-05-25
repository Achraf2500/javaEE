<!DOCTYPE html>
<%@page import="java.sql.*" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign In | Hosting</title>

        <!-- Font Icon -->
        <link href="auth/material-icon/css/material-design-iconic-font.css" rel="stylesheet" type="text/css"/>

        <link href="auth/material-icon/css/material-design-iconic-font.min.css" rel="stylesheet" type="text/css"/>
        <!-- Main css -->
        <link rel="stylesheet" href="auth/css/style.css">

    </head>
    <body>

        <div class="main">

            <!-- Sing in  Form -->
            <section class="sign-in">
                <div class="container">
                    <div class="signin-content">
                        <div class="signin-image">
                            <figure>
                                <img src="auth/3949134.jpg" alt="sing up image"/>
                            </figure>
                            <a href="/signup" class="signup-image-link">Create an account</a>
                        </div>

                        <div class="signin-form">
                            ${error}
                            <h2 class="form-title">Sign in</h2>
                            <form method="POST" action="" class="register-form">
                                <div class="form-group">
                                    <label for="email">
                                        <i class="zmdi zmdi-account material-icons-name"></i>
                                    </label> 
                                    <input type="email" required="true" name="email" id="email" placeholder="Email Address" />
                                </div>
                                <div class="form-group">
                                    <label for="pass">
                                        <i class="zmdi zmdi-lock"></i>
                                    </label> 
                                    <input type="password" required="true" name="pass" id="pass" placeholder="Password" />
                                </div>

                                <div class="form-group form-button">
                                    <input type="submit" name="signin" id="signin" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>

        </div>
    </body>
</html>