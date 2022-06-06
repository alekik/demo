package com.example.demo.Services;

import com.example.demo.persist.models.Enemy;
import com.example.demo.persist.models.User;
import com.example.demo.persist.repos.EnemyRepository;
import com.example.demo.persist.repos.HeroRepository;
import com.example.demo.persist.repos.ItemRepository;
import com.example.demo.persist.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private EnemyRepository enemyRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private HeroRepository heroRepository;
    @Autowired
    private UserRepository userRepository;

    public long adduser(String login, String password){
        User user = new User();
        user.setUsername(login);
        user.setPassword(password);
        user.setUserLevel(1);
        Optional<Enemy> enemylist = enemyRepository.findById(1L);
        ArrayList<Enemy> res = new ArrayList<>();
        enemylist.ifPresent(res::add);
        user.setCurrentEnemy(res.get(0));
        user.setCurrentEnemyHp(10);
        user.setGametime(0);
        user.setRating(0);
        user.setRole("user");
        user.setScore(0);
        userRepository.save(user);
        return user.getId();
    }

    public long findbyemail(String email){
        long res=1L;
        boolean t = false;
        List<User> lst = userRepository.findAll();
        for (User user:lst){
            if (user.getUsername().equals(email)) {
                t = true;
                break;
            }
            res+=1L;
        }
        if (t) return res;
        return 0L;
    }

    public int checkuser(long id, String password){
        int res=0;
        if (id==0L) return 0;
        Optional<User> userlist = userRepository.findById(id);
        ArrayList<User> reslst = new ArrayList<>();
        userlist.ifPresent(reslst::add);
        if (reslst.get(0).getPassword().equals(password)){
            if (reslst.get(0).getRole().equals("admin"))res=2;
            else res=1;
        }
        return res;
    }

    public User getuserbyid(Long id) {
        Optional<User> userlist = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        userlist.ifPresent(res::add);
        return res.get(0);
    }

    public void attack(long id) {
        Optional<User> userlist = userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        userlist.ifPresent(res::add);
        res.get(0).setCurrentEnemyHp(res.get(0).getCurrentEnemyHp()-1);
        userRepository.saveAndFlush(res.get(0));
    }
}
