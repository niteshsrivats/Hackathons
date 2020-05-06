//package beatmanagement.central.models.entities;
//
//import beatmanagement.central.models.constants.BeatHotspotType;
//import beatmanagement.central.models.interfaces.Location;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
///**
// * @author Nitesh (niteshsrivats.k@gmail.com)
// */
//
//@Entity(name = "beat_hotspots")
//public class BeatHotspot extends Location {
//    @Id
//    @GeneratedValue
//    String id;
//
//    @Column(nullable = false)
//    @NotNull
//    BeatHotspotType beatHotspotType;
//
//    @ManyToOne(optional = false)
//    Beat beat;
//
//    public BeatHotspot(@NotNull Double latitude, @NotNull Double longitude, @NotNull BeatHotspotType beatHotspotType, Beat beat) {
//        super(latitude, longitude);
//        this.beatHotspotType = beatHotspotType;
//        this.beat = beat;
//    }
//}
