import asyncio
import pytest
from httpx import AsyncClient
from sqlalchemy.ext.asyncio import create_async_engine, AsyncSession
from sqlalchemy.orm import sessionmaker
from app.main import app
from app.db import Base
from app import models
import os


TEST_DATABASE_URL = os.getenv("TEST_DATABASE_URL", "postgresql+asyncpg://grades_user:grades_pass@localhost:5432/grades_db")


@pytest.fixture(scope="session")
def event_loop():
    loop = asyncio.get_event_loop()
    yield loop


@pytest.fixture(scope="session")
async def initialized_db():
    engine = create_async_engine(TEST_DATABASE_URL, future=True)
    async with engine.begin() as conn:
        await conn.run_sync(Base.metadata.drop_all)
        await conn.run_sync(Base.metadata.create_all)
    yield
    await engine.dispose()


@pytest.fixture
async def client(initialized_db):
    async with AsyncClient(app=app, base_url="http://test") as ac:
       yield ac