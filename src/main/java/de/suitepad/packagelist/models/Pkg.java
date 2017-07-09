package de.suitepad.packagelist.models;

public class Pkg {

    public static Pkg create(String name, String label, String versionName, int versionCode) {
        return new Pkg(name, label, versionName, versionCode);
    }

    private final String versionName;
    private final int versionCode;
    private final String name;
    private final String label;

    private Pkg(String name, String label, String versionName, int versionCode) {
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.name = name;
        this.label = label;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pkg)) return false;

        Pkg pkg = (Pkg) o;

        if (versionCode != pkg.versionCode) return false;
        if (versionName != null ? !versionName.equals(pkg.versionName) : pkg.versionName != null)
            return false;
        return name != null
                ? name.equals(pkg.name) : pkg.name == null
                && (label != null ? label.equals(pkg.label) : pkg.label == null);

    }

    @Override
    public int hashCode() {
        int result = versionName != null ? versionName.hashCode() : 0;
        result = 31 * result + versionCode;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }
}
