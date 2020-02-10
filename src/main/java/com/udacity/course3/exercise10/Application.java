package com.udacity.course3.exercise10;

import com.udacity.course3.exercise10.model.Member;
import com.udacity.course3.exercise10.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Optional;

@SpringBootApplication
@EnableMongoRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
        return args -> {
            // STEP 1: Define Member and MemberRepository first before changing this class

            // STEP 2: create a Member record
            Member member = new Member();
            member.setFirstName("Robert");
            member.setLastName("Mondovi");
            member.setAge(23);
            member.setGender("male");

            // STEP 3: save the Member record
            memberRepository.save(member);

            // read the Member using member last name
            Optional<Member> optionalMember = memberRepository.findByLastName("Mondovi");

            if(optionalMember.isPresent()) {
                System.err.println("Member: " + optionalMember.orElse(null));
            }

            Optional<Member> optionalMember2 = memberRepository.findById(optionalMember.orElse(null).getId());

            if(optionalMember2.isPresent()) {
                System.err.println("Member found by ID: " + optionalMember2.orElse(null));
            }

        };
    }
}