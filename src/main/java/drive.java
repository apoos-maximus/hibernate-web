import com.sun.deploy.security.SessionCertStore;
import org.apoos.model.Account;
import org.apoos.model.Branch;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class drive {

    public static void main(String args []){

        Account ac = new Account();
        ac.setAccountNumber(122333044);
        ac.setHolderName("Apoorv Sachan gomat");
        List<Account> accountList = new ArrayList<>();
        accountList.add(ac);
        Branch branch = new Branch();
        branch.setBranchCode(1122);
        branch.setBranchName("kasaul");
        branch.setAccounts(accountList);
        ac.setAccountBranch(branch);



        Configuration con = new Configuration().configure(new File("src/main/resources/hibernate.cfg.xml")).addAnnotatedClass(Account.class).addAnnotatedClass(Branch.class);

        ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();

        SessionFactory sf = con.buildSessionFactory(registry);

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        session.save(ac);
        session.save(branch);

        tx.commit();
    }
}
