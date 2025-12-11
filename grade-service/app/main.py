from fastapi import FastAPI, HTTPException

app = FastAPI()

grades_db = []

@app.post("/grades")
async def add_grade(grade: dict):
    grades_db.append(grade)
    return {"message": "Grade added", "grade": grade}

@app.put("/grades/{student}/{course}")
async def update_grade(student: str, course: str, update: dict):
    for g in grades_db:
        if g["student"] == student and g["course"] == course:
            g["score"] = update["score"]
            return {"message": "Grade updated", "grade": g}
    raise HTTPException(status_code=404, detail="Grade not found")

@app.delete("/grades/{student}/{course}")
async def delete_grade(student: str, course: str):
    for g in grades_db:
        if g["student"] == student and g["course"] == course:
            grades_db.remove(g)
            return {"message": "Grade deleted"}
    raise HTTPException(status_code=404, detail="Grade not found")

@app.get("/grades/{student}")
async def get_grades(student: str):
    student_grades = [g for g in grades_db if g["student"] == student]
    if not student_grades:
        raise HTTPException(status_code=404, detail="No grades found")
    return student_grades

@app.get("/grades/{student}/average")
async def get_average(student: str):
    student_grades = [g["score"] for g in grades_db if g["student"] == student]
    if not student_grades:
        raise HTTPException(status_code=404, detail="No grades found")
    average = sum(student_grades) / len(student_grades)
    return {"student": student, "average": average}
