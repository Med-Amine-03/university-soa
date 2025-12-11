from sqlalchemy import Column, Integer, String, Float, ForeignKey
from sqlalchemy.orm import relationship
from .db import Base


class Student(Base):
    __tablename__ = "students"
    id = Column(Integer, primary_key=True)
    name = Column(String(100), nullable=False)
    # average is not stored here; we'll compute on the fly
    grades = relationship("Grade", back_populates="student", cascade="all, delete-orphan")


class Grade(Base):
    __tablename__ = "grades"
    id = Column(Integer, primary_key=True)
    student_id = Column(Integer, ForeignKey("students.id", ondelete="CASCADE"), nullable=False)
    subject = Column(String(100), nullable=False)
    score = Column(Float, nullable=False)


    student = relationship("Student", back_populates="grades")