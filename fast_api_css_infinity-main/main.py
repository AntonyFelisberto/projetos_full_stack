#run code 
#uvicorn main:app --reload

from fastapi.encoders import jsonable_encoder
from fastapi import FastAPI, HTTPException
from typing import Literal, Optional
from pydantic import BaseModel
from uuid import uuid4
import random
import json
import os


app = FastAPI()

class Game (BaseModel):
    name:str
    price: float
    game_id: Optional[str] = uuid4().hex
    genre: Literal["non-fiction","fiction"]
    
GAMES_FILE = "games.json"

GAMES_DATABASE = []

if os.path.exists(GAMES_FILE):
    with open(GAMES_FILE,"r") as f:
        GAMES_DATABASE = json.load(f)


@app.get("/")
async def home():
    return "Welcome to my games store"

@app.get("/list-games")
async def list_games():
    return {"games":GAMES_DATABASE}

@app.get("/list-games-by-index/{index}")
async def list_games_by_index(index:int):
    if index < 0 or index >= len(GAMES_DATABASE):
        raise HTTPException(404,"Index out of range")
    else:
        return {"games":GAMES_DATABASE[index]}
    
@app.get("/get-random-game")
async def get_random_game():
    return random.choice(GAMES_DATABASE)

@app.post("/add-game")
async def add_game(game:Game):
    game.game_id = uuid4().hex
    json_game = jsonable_encoder(game)
    GAMES_DATABASE.append(json_game)
    with open(GAMES_FILE,"w") as f:
        json.dump(GAMES_DATABASE,f)
    return {"message":f"game {game.name} was added"}