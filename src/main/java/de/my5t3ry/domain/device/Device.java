package de.my5t3ry.domain.device;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * created by: sascha.bast
 * since: 30.08.17
 */
@Entity
public class Device {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    final String name;

    final int count;

    public Device(final String name, final int count) {
        this.name = name;
        this.count = count;
    }
}
