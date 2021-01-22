package devlaunchers.rifthunters;


class Vector3 {

    public int x, y, z;

    public Vector3(int _x, int _y, int _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    public String toString() {
        return x+", "+y+", "+z;
    }
}