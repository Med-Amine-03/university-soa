from sqlalchemy.ext.asyncio import create_async_engine, AsyncSession
from sqlalchemy.orm import sessionmaker, declarative_base
from .config import settings


engine = create_async_engine(settings.database_url, future=True)
AsyncSessionLocal = sessionmaker(bind=engine, class_=AsyncSession, expire_on_commit=False)
Base = declarative_base()


async def init_db():
# create tables (simple approach for this task)
    async with engine.begin() as conn:
        await conn.run_sync(Base.metadata.create_all)