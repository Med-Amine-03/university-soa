from fastapi import Header, HTTPException, Depends
import jwt
from .config import settings


async def verify_jwt(authorization: str = Header(None)):
    if settings.auth_disabled:
    # Return a dummy user object
        return {"sub": "test-user", "roles": ["tester"]}
    if not authorization:
        raise HTTPException(status_code=401, detail="Missing Authorization header")
    scheme, _, token = authorization.partition(" ")
    if scheme.lower() != "bearer" or not token:
        raise HTTPException(status_code=401, detail="Invalid auth scheme")
    try:
        payload = jwt.decode(token, settings.jwt_secret, algorithms=[settings.jwt_algorithm])
        return payload
    except jwt.PyJWTError:
        raise HTTPException(status_code=403, detail="Invalid token")