package example.Database;

import example.model.Forage;

import java.util.List;

public interface ForageDAO extends AutoCloseable{
    public void saveForage(Forage f);
    public void deleteForage(Forage f);
    public void updateForage(Forage f);
    public List<Forage> getForage();
}