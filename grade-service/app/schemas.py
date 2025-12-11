from pydantic import BaseModel, Field
from typing import Optional, List


class GradeCreate(BaseModel):
    subject: str = Field(..., example="Math")
    score: float = Field(..., ge=0, le=20, example=15.5)


class GradeUpdate(BaseModel):
    subject: Optional[str]
    score: Optional[float]


class GradeOut(BaseModel):
    id: int
    student_id: int
    subject: str
    score: float


class Config:
    orm_mode = True


class GradesListOut(BaseModel):
    grades: List[GradeOut]
    average: Optional[float]