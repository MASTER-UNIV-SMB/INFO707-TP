package fr.enzoharisthomasclement.utils;

import java.io.*;
import java.util.Base64;

public class Serializer<T> {

    public String serialize(T object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            final byte[] byteArray = bos.toByteArray();
            return Base64.getEncoder().encodeToString(byteArray);
        }
    }

    public T deserialize(String byteString) throws IOException, ClassNotFoundException {
        final byte[] bytes = Base64.getDecoder().decode(byteString);
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes); ObjectInput in = new ObjectInputStream(bis)) {
            return (T) in.readObject();
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
