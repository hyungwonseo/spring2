package dw.gameshop_jwt.model;

import jakarta.persistence.*;

@Entity
@Table(name="image_file")
public class ImageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    @Lob
    @Column(name = "data", columnDefinition = "LONGBLOB", unique = true)
    private byte[] data;

    public ImageFile() {
    }

    public ImageFile(Long id, String filename, byte[] data) {
        this.id = id;
        this.filename = filename;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
