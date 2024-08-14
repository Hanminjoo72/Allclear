package com.project.allclear_course;

import com.project.allclear_course.domain.entity.College;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CollegeEntityTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testCollegeEntityAuditing() {
        // Given
        College college = new College();
        college.setName("인문대학");
        college.setTotalNumber(1500);

        // When
        entityManager.persist(college);
        entityManager.flush();
        entityManager.clear();

        // Then
        College savedCollege = entityManager.find(College.class, college.getId());
        assertThat(savedCollege).isNotNull();
        assertThat(savedCollege.getName()).isEqualTo("인문대학");
        assertThat(savedCollege.getTotalNumber()).isEqualTo(1500);
        assertThat(savedCollege.getCreatedDate()).isNotNull();
        assertThat(savedCollege.getModifiedDate()).isNotNull();
    }
}
