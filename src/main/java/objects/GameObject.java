package objects;

import enums.ObjectType;

public class GameObject {
    private ObjectType type;
    private Position position;

    public GameObject(ObjectType type) {
        this.setType(type);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ObjectType getType() {
        return type;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }
}
