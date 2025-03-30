<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Produit</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<h1>Ajouter un Produit</h1>

<form action="mycontroller" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="action" value="addProduct">

    <label for="nom">Nom du Produit :</label>
    <input type="text" id="nom" name="nom" required>

    <label for="description">Description :</label>
    <textarea id="description" name="description" required></textarea>

    <label for="prix">Prix :</label>
    <input type="number" id="prix" name="prix" required>

    <label for="image">Image du Produit :</label>
    <input type="file" id="image" name="image" accept="image/*" required>

    <button type="submit">Ajouter Produit</button>
</form>

</body>
</html>
