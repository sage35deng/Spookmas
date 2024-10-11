package src;

import src.tile.Couch;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Characters entity, Couch couch) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;
        int couchLeft = couch.couchX + gp.tileSize;
        int couchRight = couch.couchX + couch.couchWidth + gp.tileSize;
        int couchBot = couch.couchY + couch.couchHeight;
        int couchTop = couch.couchY;

        switch (entity.setDirection) {
            case "up":

                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                // tileCheck
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                if(gp.tileM.currentMap.equals("downStairs")){
                    entityTopRow = (entityTopWorldY - entity.speed);
                    if (entityTopRow >= couchBot) {
                        if (entityLeftWorldX <= couchLeft && entityRightWorldX >= couchLeft) {
                            if (entityTopRow - couch.couchY - couch.couchHeight - entity.solidArea.height <= 0) {
                                entity.collisionOn = true;
                            }
                        } else if (entityRightWorldX >= couchRight && entityLeftWorldX <= couchRight) {
                            if (entityTopRow - couch.couchY - couch.couchHeight - entity.solidArea.height <= 0) {
                                entity.collisionOn = true;
                            }
                        } else if (entityLeftWorldX >= couchLeft && entityRightWorldX <= couchRight) {
                            if (entityTopRow - couch.couchY - couch.couchHeight - entity.solidArea.height <= 0) {
                                entity.collisionOn = true;
                            }
                        }
                    }
                }
            break;
                
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                if(gp.tileM.currentMap.equals("downStairs")){
                    //Check couch DONT ASK ME HOW THIS WORKS
                    entityBottomRow = (entityTopWorldY + entity.speed);
                    if(entityBottomWorldY>couchBot){
                        entityBottomRow = (entityBottomWorldY + entity.speed);
                    }
                    if (entityBottomRow >= couch.couchY + gp.tileSize && entity.worldY< couch.couchY ) {
                        if (entityLeftWorldX <= couchLeft && entityRightWorldX >= couchLeft) {
                            if (entityBottomRow - couch.couchY >= 0) {
                                entity.collisionOn = true;

                            }
                        } else if (entityRightWorldX >= couchRight && entityLeftWorldX <= couchRight) {
                            if (entityBottomRow - couch.couchY >= 0) {
                                entity.collisionOn = true;
                            }
                        } else if (entityLeftWorldX >= couchLeft && entityRightWorldX <= couchRight) {
                            if (entityBottomRow - couch.couchY >= 0) {
                                entity.collisionOn = true;
                            }
                        }
                    }
                }
            break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[entityLeftCol][entityBottomRow];

                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                if(gp.tileM.currentMap.equals("downStairs")){
                    //Check couch DONT ASK ME HOW THIS WORKS
                    entityLeftCol = (entityLeftWorldX - entity.speed);
                    couchTop = couchTop - entity.solidArea.x - entity.solidArea.height;
                    couchTop +=  2*48+24;//IDK WHERE TF I GOT THIS NUMBERS BUT IT WORKS

                    if (entityLeftCol <= couchRight && entity.worldX > couch.couchX ) {
                        if (entityTopWorldY < couchTop && entityBottomWorldY > couchTop) {
                            if (entityLeftCol - couchRight <= 0) {
                                entity.collisionOn = true;
                            }
                        } else if (entityTopWorldY <= couchBot+24 && entityBottomWorldY > couchBot+24) {
                            if (entityLeftCol - couchRight <= 0) {
                                entity.collisionOn = true;
                            }
                        } else if (entityTopWorldY > couchTop  && entityBottomWorldY < couchBot+24) {
                            if (entityLeftCol - couchRight <= 0) {
                                entity.collisionOn = true;
                            }
                        }
                    }
                }
                
            break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                if(gp.tileM.currentMap.equals("downStairs")){
                    entityRightCol = (entityRightWorldX - entity.speed);
                    couchTop = couchTop - entity.solidArea.x - entity.solidArea.height;

                    couchTop +=  2*48+24;//IDK WHERE TF I GOT THIS NUMBER BUT IT WORKS

                    if (entityRightCol <= couchLeft && entity.worldX +5 >couch.couchX) {
                        if (entityTopWorldY < couchTop && entityBottomWorldY > couchTop) {
                            if (entityRightCol - couchLeft <= 0) {
                                entity.collisionOn = true;
                            }
                        } else if (entityTopWorldY <= couchBot+24 && entityBottomWorldY > couchBot+24) {
                            if (entityRightCol - couchLeft <= 0) {
                                entity.collisionOn = true;
                            }
                        } else if (entityTopWorldY > couchTop  && entityBottomWorldY < couchBot+24) {
                            if (entityRightCol - couchLeft <= 0) {
                                entity.collisionOn = true;
                            }
                        }
                    }
                }
                
            break;
        }
    }

    
}

