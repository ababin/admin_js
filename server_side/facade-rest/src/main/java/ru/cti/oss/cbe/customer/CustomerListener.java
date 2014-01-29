// license-header java merge-point
package ru.cti.oss.cbe.customer;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Callback Listener for Entity POJO EJB ru.cti.oss.cbe.customer.Customer
 *
 * @see ru.cti.oss.cbe.customer.Customer
 */
public class CustomerListener {
    @javax.persistence.PersistenceContext(unitName = "iptvmw")
    EntityManager em;

    private Log log = LogFactory.getLog(CustomerListener.class);


    /**
     * Default public no-args constructor
     */
    public CustomerListener() {
        // empty constructor
    }

    @javax.persistence.PrePersist
    public void prePersist(ru.cti.oss.cbe.customer.Customer customer) {
        // pre persist implementation
    }

    @javax.persistence.PostPersist
    public void postPersist(ru.cti.oss.cbe.customer.Customer customer) {
        // post persist implementation
    }

    @javax.persistence.PreRemove
    public void preRemove(ru.cti.oss.cbe.customer.Customer customer) {
        // pre remove implementation
    }

    @javax.persistence.PostRemove
    public void postRemove(ru.cti.oss.cbe.customer.Customer customer) {
        // post remove implementation
    }


    //TODO Перенести   preUpdate наSpring
   /* @javax.persistence.PreUpdate
    public void preUpdate(ru.cti.oss.cbe.customer.Customer customer) {
        try {
            // избавляемся от DAO компонентов
            *//*
               InitialContext context = new InitialContext ();
               CustomerDao customerDao=(CustomerDao)context.lookup(ProjectInfoBean.getApplicationName() + "/CustomerDao/local");
               Customer oldCustomer=customerDao.load(customer.getId());
               *//*
            Customer oldCustomer = em.find(Customer.class, customer.getId());
            if (oldCustomer.getCustomerStatus() != null && !oldCustomer.getCustomerStatus().equals(customer.getCustomerStatus())) {
                CustomerManagementService service = ServiceLocator.getInstance().findCustomerManagementServiceAdapterLocal();

                CustomerStatus status = customer.getCustomerStatus();
                int billingStatus;

                if (CustomerStatus.ACTIVE.equals(status)) {
                    billingStatus = CustomerAccountStatus.ACTIVE;
                } else if (CustomerStatus.AWAITING_AUTHORIZATION.equals(status)) {
                    billingStatus = CustomerAccountStatus.AWAITING_AUTHORIZATION;
                } else if (CustomerStatus.BLOCKED_BY_CUSTOMER.equals(status)) {
                    billingStatus = CustomerAccountStatus.BLOCKED_BY_USER;
                } else if (CustomerStatus.BLOCKED_BY_FINANCE.equals(status)) {
                    billingStatus = CustomerAccountStatus.BLOCKED_BY_FINANCE;
                } else {
                    billingStatus = CustomerAccountStatus.INACTIVE;
                }
                service.changeCustomerAccountStatus(customer.getName(), billingStatus);
            }
        } catch (Exception exc) {
            LoggerUtilBean.logWarn(log, "Can't send Customer status to billing", exc);
        }
    }*/

    @javax.persistence.PostUpdate
    public void postUpdate(ru.cti.oss.cbe.customer.Customer customer) {
    }

    @javax.persistence.PostLoad
    public void postLoad(ru.cti.oss.cbe.customer.Customer customer) {
        // post load implementation
    }
}
