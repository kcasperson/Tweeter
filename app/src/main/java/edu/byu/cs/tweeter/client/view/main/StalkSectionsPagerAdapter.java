package edu.byu.cs.tweeter.client.view.main;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.view.main.followers.FollowersFragment;
import edu.byu.cs.tweeter.client.view.main.following.FollowingFragment;
import edu.byu.cs.tweeter.client.view.main.userNavigate.StoryFragment;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import org.jetbrains.annotations.NotNull;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to one of the sections/tabs/pages
 * of the Main Activity.
 */
class StalkSectionsPagerAdapter extends SectionsPagerAdapter {

    private static final int STORY_FRAGMENT_POSITION = 0;
    private static final int FOLLOWING_FRAGMENT_POSITION = 1;
    private static final int FOLLOWERS_FRAGMENT_POSITION = 2;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.storyTabTitle, R.string.followingTabTitle, R.string.followersTabTitle};
    private final Context mContext;
    private final User user; //in this case, this is the user we are wanting to see, not the one logged in
    private final AuthToken authToken;

    public StalkSectionsPagerAdapter(Context context, FragmentManager fm, User user, AuthToken authToken) {
        super(context,fm,user,authToken);
        mContext = context;
        this.user = user;
        this.authToken = authToken;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        if (position == FOLLOWING_FRAGMENT_POSITION) {
            return FollowingFragment.newInstance(user, authToken,mContext);
        } else if(position == FOLLOWERS_FRAGMENT_POSITION) {
            return FollowersFragment.newInstance(user, authToken,mContext);
        } else if(position == STORY_FRAGMENT_POSITION){
            return StoryFragment.newInstance(user,authToken);
        }
        else {
            return PlaceholderFragment.newInstance(position + 1);
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}