from sqlalchemy import select, func
from sqlalchemy.ext.asyncio import AsyncSession
from . import models, schemas


async def get_student(session: AsyncSession, student_id: int):
    q = await session.get(models.Student, student_id)
    return q


async def create_student_if_not_exists(session: AsyncSession, name: str = "Unnamed"):
    # Very simple: check first student with name 'Unnamed' is not ideal but OK for testing
    stmt = select(models.Student).where(models.Student.name == name)
    res = await session.execute(stmt)
    student = res.scalars().first()
    if student:
        return student
    student = models.Student(name=name)
    session.add(student)
    await session.commit()
    await session.refresh(student)
    return student


async def add_grade(session: AsyncSession, student_id: int, grade: schemas.GradeCreate):
    g = models.Grade(student_id=student_id, subject=grade.subject, score=grade.score)
    session.add(g)
    await session.commit()
    await session.refresh(g)
    return g


async def update_grade(session: AsyncSession, grade_id: int, data: schemas.GradeUpdate):
    g = await session.get(models.Grade, grade_id)
    if g is None:
        return None
    if data.subject is not None:
        g.subject = data.subject
    if data.score is not None:
        g.score = data.score
    session.add(g)
    await session.commit()
    await session.refresh(g)
    return g


async def delete_grade(session: AsyncSession, grade_id: int):
    g = await session.get(models.Grade, grade_id)
    if g is None:
        return False
    await session.delete(g)
    await session.commit()
    return True


async def list_grades_and_average(session: AsyncSession, student_id: int):
    stmt = select(models.Grade).where(models.Grade.student_id == student_id)
    res = await session.execute(stmt)
    grades = res.scalars().all()
    # compute average
    if not grades:
        avg = None
    else:
        avg = sum(g.score for g in grades) / len(grades)
    return grades, avg