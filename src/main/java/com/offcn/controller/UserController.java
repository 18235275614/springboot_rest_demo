package com.offcn.controller;

import com.offcn.pojo.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private List<User> list = Collections.synchronizedList(new ArrayList<>());

    @PostMapping("/")
    //@RequestMapping(value = "/",method = RequestMethod.POST)
    public String add(@RequestBody User user){
        try {
            list.add(user);
            return "添加成功!!!";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败!!!";
        }
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable(name = "id") Long id){
        for (User user:list){
            if (user.getId().longValue() == id.longValue()){
                return user;
            }
        }
        return null;
    }

    @PutMapping("/{id}")
    public String update(@RequestBody User user,@PathVariable(name = "id") Long id){
        try {
            for (User oldUser:list){
                if (oldUser.getId().longValue() == id.longValue()){
                    oldUser.setName(user.getName());
                    oldUser.setAge(user.getAge());
                }
            }
            return "修改成功!!!";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败!!!";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(name = "id") Long id){
        try {
            list.remove(this.findOne(id));
            return "删除成功!!!";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败!!!";
        }
    }

    @GetMapping("/")
    public List<User> findAll(){
        return list;
    }


}
