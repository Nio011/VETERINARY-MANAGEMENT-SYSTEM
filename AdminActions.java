//This helps to achieve abstraction by defining an interface that specifies the actions an admin can perform for most of the classes

public interface AdminActions { //Actions to be implemented by Clients and Services
    void add();
    void edit();
    void delete();
    void search(); 
    void viewAll();
}
