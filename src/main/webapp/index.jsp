<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Authentification</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>E-Commerce</h1>
<h3>Merci de vous connecter</h3>
<div class="login-container">
    <h2>Connexion</h2>
    <form action="mycontroller" method="get">
        <label for="login">Login :</label>
        <input type="text" id="login" name="login" required>

        <label for="password">Mot de passe :</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Se connecter</button>
    </form>
</div>
</body>
</html>

