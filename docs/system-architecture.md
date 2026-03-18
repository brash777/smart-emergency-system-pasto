# System Architecture

## Overview

The system follows a multi-layer architecture:

User
↓
Frontend (Web Application)
↓
Backend (REST API - Spring Boot)
↓
AI Service (Image Classification)
↓
Database (PostgreSQL)

## Components

### Frontend
- User interface
- Image upload
- Location capture

### Backend
- Receives requests
- Processes data
- Communicates with AI service
- Stores information in database

### AI Service
- Analyzes images
- Classifies emergency type

### Database
- Stores users
- Stores emergency reports
- Stores classification results
