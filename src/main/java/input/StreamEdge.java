package input;

import java.io.Serializable;


public class StreamEdge implements Serializable, Comparable<StreamEdge> {
    private static final long serialVersionUID = -3733214465018614013L;
    private final Long src;
    private final Long dest;

    public StreamEdge(long src, long dest) {
        this.src = src;
        this.dest = dest;
    }

    public Long getSource() {
        return this.src;
    }

    public Long getDestination() {
        return this.dest;
    }


    public String toString() {
        return this.src + " " + this.dest;
    }

    public int compareTo(StreamEdge o) {
        if (this.src.equals(o.src) && this.dest.equals(o.dest))
            return 0;
        else if (this.src.equals(o.dest) && this.dest.equals(o.src))
            return 0;
        else
            return -1;
    }
}
