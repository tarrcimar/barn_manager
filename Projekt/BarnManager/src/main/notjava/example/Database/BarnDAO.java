package example.Database;

import example.model.Barn;

import java.util.List;

public interface BarnDAO extends AutoCloseable{
    public void saveBarn(Barn b);
    public void deleteBarn(Barn b);
    public void updateBarn(Barn b);
    public List<Barn> getBarns();
    public Barn getBarnByID(long id);
}
