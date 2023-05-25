
<%@page import="java.sql.*" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up | Hosting</title>
        <link href="auth/material-icon/css/material-design-iconic-font.css" rel="stylesheet" type="text/css"/>
        <link href="auth/material-icon/css/material-design-iconic-font.min.css" rel="stylesheet" type="text/css"/>

        <!-- Main css -->
        <link rel="stylesheet" href="auth/css/style.css">
    </head>
    <body>

        <div class="main">

            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            ${error}
                            <h2 class="form-title">Sign up</h2>
                            <form method="POST" action="signup" class="register-form" id="register-form">
                                <div class="form-group">
                                    <label for="name">
                                        <i class="zmdi zmdi-account material-icons-name"></i>
                                    </label> 
                                    <input type="text" required name="name" id="name" placeholder="Your Name" />
                                </div>
                                <div class="form-group">
                                    <label for="email">
                                        <i class="zmdi zmdi-email"></i>
                                    </label> 
                                    <input type="email" required name="email" id="email" placeholder="Your Email" />
                                </div>
                                <div class="form-group">
                                    <label for="pass">
                                        <i class="zmdi zmdi-lock"></i>
                                    </label> 
                                    <input type="password" name="pass" id="pass" placeholder="Password" />
                                </div>

                                <div class="form-group form-button">
                                    <input type="submit" name="signup" id="signup" class="form-submit" value="Register" />
                                </div>
                            </form>
                        </div>
                        <div class="signup-image">
                            <figure>
                                <img src="auth/lo.png" alt="sing up image"/>
                            </figure>
                            
                            <a href="login" class="signup-image-link">I am already member</a>
                        </div>
                    </div>
                </div>
            </section>


        </div>
    </body>
</html>