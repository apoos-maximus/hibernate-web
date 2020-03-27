import org.apoos.model.Account;
import org.apoos.model.Branch;
import org.apoos.service.AccountService;
import org.apoos.service.BranchService;
import java.util.ArrayList;
import java.util.List;

public class drive {

    public static void main(String args []){

        Account ac = new Account();
        Branch brc = new Branch();

        AccountService accountService = new AccountService();
        BranchService branchService = new BranchService();

        brc.setBranchCode(1121);
        brc.setBranchName("Kasaul");
        ac.setAccountNumber(1111);
        ac.setHolderName("apoorv");
        ac.setAccountBranch(brc);
        brc.addAccount(ac);

        branchService.persist(brc);
        accountService.persist(ac);

        ac.setAccountNumber(2222);
        ac.setHolderName("deva");
        ac.setAccountBranch(brc);
        brc.addAccount(ac);

        accountService.persist(ac);

        brc.setBranchCode(1234);
        brc.setBranchName("Mysore");

        ac.setAccountNumber(3333);
        ac.setHolderName("Rabindra");
        ac.setAccountBranch(brc);

        accountService.persist(ac);

        ac.setAccountNumber(4444);
        ac.setHolderName("djinesh");
        ac.setAccountBranch(brc);

        accountService.persist(ac);

//        System.out.println(accountService.findAll());
//
//        List<Account> acs = (List<Account>) branchService.findById(1121).getAccounts();
//        System.out.println(acs.get(0));

        System.out.println(accountService.findAll());
        System.out.println(branchService.findById(1121).getAccounts());








    }
}
