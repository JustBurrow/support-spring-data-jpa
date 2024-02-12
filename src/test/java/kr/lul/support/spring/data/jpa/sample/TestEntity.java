package kr.lul.support.spring.data.jpa.sample;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.util.Objects.requireNonNull;

@Entity(name = "Test")
@Table(name = "`test`")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "`id`", unique = true, nullable = false, insertable = false, updatable = false)
    private long id = 0L;
    @Column(name = "`uuid`")
    private UUID uuid;
    @Column(name = "`instant`")
    private Instant instant;
    @Column(name = "`zone_id`")
    private ZoneId zoneId;
    @Column(name = "`zdt`")
    private ZonedDateTime zonedDateTime;

    public TestEntity() {
    }

    public TestEntity(UUID uuid, Instant instant, ZoneId zoneId, ZonedDateTime zonedDateTime) {
        this.uuid = requireNonNull(uuid);
        this.instant = requireNonNull(instant);
        this.zoneId = requireNonNull(zoneId);
        this.zonedDateTime = requireNonNull(zonedDateTime);
    }

    public long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getInstant() {
        return instant;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id == ((TestEntity) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TestEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("uuid=" + uuid)
                .add("instant=" + instant)
                .add("zoneId=" + zoneId)
                .add("zonedDateTime=" + zonedDateTime)
                .toString();
    }
}
