package cn.skio.car_lease.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class LeaseInfo extends AbstractEntity{

    String tenantName;

    protected LeaseInfo() {
        this.tenantName = null;
    }
}
