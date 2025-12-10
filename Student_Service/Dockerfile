FROM node:20-alpine AS base

WORKDIR /usr/src/app

# Install dumb-init and wget
RUN apk add --no-cache dumb-init wget

# Copy package files first (Docker cache optimization)
COPY package*.json ./

# Install dependencies inside the container (production mode)
RUN npm ci --omit=dev

# Copy the rest of the application
COPY . .

# Environment variables
ENV NODE_ENV=production
ENV PORT=4000

# Create non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Expose port
EXPOSE 4000

# Healthcheck
HEALTHCHECK --interval=30s --timeout=5s --start-period=15s --retries=3 \
  CMD wget -qO- http://localhost:4000/students || exit 1

# Start the app
CMD ["dumb-init", "node", "src/server.js"]
