import org.apoos.model.Account;
import org.apoos.model.Branch;
import org.apoos.service.AccountService;
import org.apoos.service.BranchService;
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

        AccountService accountService = new AccountService();
        BranchService branchService = new BranchService();

        branchService.persist(branch);
        branch.setBranchCode(112);
        branch.setBranchName("Apo");
        branchService.persist(branch);

        System.out.println(branchService.findAll());
    }
}
