package core.actions;

public class Action {
    private ActionUser user;
    private ActionTarget target;
    private Resolvable resolvable;

    public Action(ActionUser user, ActionTarget target, Resolvable resolvable) {
        this.user = user;
        this.target = target;
        this.resolvable = resolvable;
    }

    public ActionUser resolve() {
        resolvable.resolve(user, target);
    }

    public Resolvable getResolvable() {
        return resolvable;
    }

    public ActionUser getUser() {
        return user;
    }

    public ActionTarget getTarget() {
        return target;
    }
}
