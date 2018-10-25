package mt.tweets.kmer.domain;


import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = PRIVATE, force = true)
@RequiredArgsConstructor
public class Employee {

    private final String name;

    private final String Salary;

    private final int age;

    private final String designation;
}
