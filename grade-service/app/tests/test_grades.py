import pytest
from httpx import AsyncClient


@pytest.mark.asyncio
async def test_add_list_delete_grade(client: AsyncClient):
# add a grade
    resp = await client.post("/api/students/1/grades", json={"subject": "Math", "score": 14})
    assert resp.status_code == 200
    g = resp.json()
    assert g["subject"] == "Math"


# list grades
    resp = await client.get("/api/students/1/grades")
    assert resp.status_code == 200
    data = resp.json()
    assert data["average"] == 14
    assert len(data["grades"]) >= 1


    grade_id = data["grades"][0]["id"]


    # update grade
    resp = await client.put(f"/api/grades/{grade_id}", json={"score": 16})
    assert resp.status_code == 200
    assert resp.json()["score"] == 16


    # delete grade
    resp = await client.delete(f"/api/grades/{grade_id}")
    assert resp.status_code == 204


    # list again
    resp = await client.get("/api/students/1/grades")
    assert resp.status_code == 200
    data = resp.json()
    # average is None or missing grades
    assert data["average"] is None or data["average"] == 0 or data["grades"] == []