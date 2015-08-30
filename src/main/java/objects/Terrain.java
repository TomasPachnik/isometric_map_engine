package objects;

import enums.Type;

public abstract class Terrain {
    private Type type;

    public Terrain(Type type) {
        this.setType(type);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
