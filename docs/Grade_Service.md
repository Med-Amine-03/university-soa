# Manuel d'utilisation : Grade Service API

Ce manuel explique comment tester les endpoints de l'API **Grade Service** avec **Postman**.

---

## **Prérequis**

- FastAPI serveur en cours d'exécution :

```bash
uvicorn app.main:app --reload --port 8000
Postman installé : https://www.postman.com/downloads/

URL de base : http://127.0.0.1:8000

Endpoints et Tests
1. Ajouter une note pour un étudiant
Méthode : POST

URL : /grades

Body (JSON) :

```json
{
  "student": "Alice",
  "course": "Math",
  "score": 95
}
```
Réponse attendue :

```json
{
  "message": "Grade added",
  "grade": {
    "student": "Alice",
    "course": "Math",
    "score": 95
  }
}
```

2. Modifier une note
Méthode : PUT

URL : /grades/{student}/{course}
Exemple : /grades/Alice/Math

Body (JSON) :

```json
{
  "score": 98
}
```
Réponse attendue :

```json
{
  "message": "Grade updated",
  "grade": {
    "student": "Alice",
    "course": "Math",
    "score": 98
  }
}
```

3. Supprimer une note
Méthode : DELETE

URL : /grades/{student}/{course}
Exemple : /grades/Alice/Math

Réponse attendue :

```json
{
  "message": "Grade deleted"
}
```

4. Lister les notes d’un étudiant
Méthode : GET

URL : /grades/{student}
Exemple : /grades/Alice

Réponse attendue :

```json
[
  {
    "student": "Alice",
    "course": "Math",
    "score": 98
  },
  {
    "student": "Alice",
    "course": "Science",
    "score": 92
  }
]
```

5. Calcul automatique de la moyenne
Méthode : GET

URL : /grades/{student}/average
Exemple : /grades/Alice/average

Réponse attendue :

```json
{
  "student": "Alice",
  "average": 95
}
```


