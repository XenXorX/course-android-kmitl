package kmitl.lab07.chanapat58070024.mylazyinstagram.model;

public class UserProfile {
    private String bio;
    private int follower;
    private int following;
    private boolean isFollow;
    private int post;

    private String urlProfile;
    private String user;

    private UserPost[] posts;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UserPost[] getPosts() {
        return posts;
    }

    public void setPosts(UserPost[] posts) {
        this.posts = posts;
    }
}
