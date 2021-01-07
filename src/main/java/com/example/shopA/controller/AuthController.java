package com.example.shopA.controller;

import com.example.shopA.model.AddOns;
import com.example.shopA.model.Credentials;
import com.example.shopA.model.Shop;
import com.example.shopA.model.User;
import com.example.shopA.payload.request.LoginRequest;
import com.example.shopA.payload.request.SignupRequest;
import com.example.shopA.payload.response.JwtResponse;
import com.example.shopA.payload.response.MessageResponse;
import com.example.shopA.repository.AddOnsRepository;
import com.example.shopA.repository.CredentialRepository;
import com.example.shopA.repository.ShopRepository;
import com.example.shopA.repository.UserRepository;
import com.example.shopA.service.Impls.UserDetailsImpl;
import com.example.shopA.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CredentialRepository credentialRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    AddOnsRepository addOnsRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/deneme")
    public String allAccess() {
        return "accessd";
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws ParseException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getMailAddress(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        //TODO success response ile degistir
        return ResponseEntity.ok(new JwtResponse(jwt,
                                                userDetails.getId(),
                                                userDetails.getUsername(),
                                                userDetails.getEmail()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        //TODO change with queries later
        Optional<User> byShopName = userRepository.findByShopName(signupRequest.getShopname());
        if (byShopName.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: This shop name is already taken"));
        }
        Optional<User> byMailAddress = userRepository.findByMailAddress(signupRequest.getMail());
        if (byMailAddress.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: This email is already taken"));
        }
        Credentials credentials = new Credentials(signupRequest.getMail(), passwordEncoder.encode(signupRequest.getPassword()));
        Credentials newCredentials = credentialRepository.save(credentials);

        setDefaultShopFeatures(signupRequest, newCredentials.getId());

        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }

    private void setDefaultShopFeatures(SignupRequest signupRequest, String id) {
        User user = new User(id, signupRequest.getShopname(), signupRequest.getMail());
        Optional<AddOns> addOn = addOnsRepository.findByAddonName("Benim Sayfam"); //TODO read from enum

        List<AddOns> listOfAddons = new ArrayList<>();
        if (addOn.isPresent()) {
            listOfAddons.add(addOn.get());
        }

        Shop shop = new Shop(id, signupRequest.getShopname(), listOfAddons);
        userRepository.save(user);
        shopRepository.save(shop);
    }
}
