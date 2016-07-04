package ms.senac.br.appsenac.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ms.senac.br.appsenac.fragment.CalendarioFragment;
import ms.senac.br.appsenac.fragment.FrequenciaFragment;
import ms.senac.br.appsenac.fragment.NotaFragment;

/**
 * Created by Aluno on 03/03/2016.
 */
public class AdapterAreaAluno extends FragmentPagerAdapter {
    private Context context;

    public AdapterAreaAluno(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "Notas";
        } else if (position == 1){
            return "Frequência";
        }
        return "Calendário";
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if (position == 0) {
            f = new NotaFragment();
        } else if (position == 1) {
            f = new FrequenciaFragment();
        } else {
            f = new CalendarioFragment();
        }
        return f;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
