package de.my5t3ry.domain.projects;

import de.my5t3ry.domain.device.Device;
import de.my5t3ry.domain.device.DeviceManufacturer;

import javax.persistence.*;
import java.util.List;

/**
 * created by: sascha.bast
 * since: 29.08.17
 */
@Entity
public class ProjectFile {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String name;

    @ManyToMany
    protected List<Device> internalDevices;

    @ManyToMany
    protected List<Device> externalDevices;

    @ManyToMany
    protected List<DeviceManufacturer> manufacturers;

    protected Integer groupTracks;
    protected Integer midiTracks;
    protected Integer audioTracks;

    public Integer getTotalTracks() {
        return groupTracks + midiTracks + audioTracks;
    }
}
